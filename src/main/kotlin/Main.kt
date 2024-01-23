import org.openrndr.MouseButton
import org.openrndr.application
import org.openrndr.draw.loadImage
import org.openrndr.drawImage
import kotlin.math.floor
import org.openrndr.color.ColorRGBa as ColourRGBa

typealias Coord = Pair<Int, Int>

fun main() = chess()

fun chess() =
	application {
		configure {
			width = 640
			height = 640
		}

		program {
			val board = drawImage(drawer.context.width, drawer.context.height) { drawer.drawChessBoard() }
			val pieces = loadImage("data/images/pieces.png")
			val w = drawer.context.width.toDouble() / 8.0
			val h = drawer.context.height.toDouble() / 8.0

			val gameState = Board.startingPosition.copy()
			var onScreen = false
			var highlighted: Pair<Coord, Piece>? = null
			var attackable: List<Coord> = listOf()

			mouse.buttonDown.listen {
				when (it.button) {
					MouseButton.LEFT -> {
						val cand = screenToMap(mouse.position)
						println(cand)

						when {
							attackable.contains(cand) -> {
								move(gameState, highlighted!!.first, cand)
								highlighted = null
								attackable = listOf()
							}
							gameState[cand] != null -> {
								highlighted = Pair(cand, gameState[cand]!!)
								attackable = moves(gameState, cand)
							}
							else -> {
								highlighted == null
								attackable = listOf()
							}
						}

						println(highlighted)
					}
					else -> {}
				}
			}

			mouse.exited.listen {
				onScreen = false
			}
			mouse.entered.listen {
				onScreen = true
			}

			extend {
				val corner = mouse.position.map { x: Double -> floor(x / 80.0) * 80.0 }
				drawer.strokeWeight = -1.0

				drawer.image(board)

				if (onScreen) {
					drawer.fill = ColourRGBa.MAGENTA.copy(alpha = 0.3)
					drawer.rectangle(corner, 80.0)
				}

				drawer.fill = ColourRGBa.GREEN.copy(alpha = 0.3)
				if (highlighted != null) {
					val (pos, _) = highlighted!!
					for ((dx, dy) in attackable) {
						val hlCorner = mapToScreen(Pair(dx, dy))
						drawer.rectangle(hlCorner, 80.0)
					}
				}
				drawer.drawBoardState(gameState, pieces, w, h)
			}
		}
	}
