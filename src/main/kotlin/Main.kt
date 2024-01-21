import org.openrndr.MouseButton
import org.openrndr.application
import org.openrndr.draw.loadImage
import org.openrndr.drawImage
import org.openrndr.math.Vector2
import kotlin.math.floor
import org.openrndr.color.ColorRGBa as ColourRGBa

typealias Coord = Pair<Int, Int>

fun main() =
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
			var highlighted: Pair<Pair<Int, Int>, Piece>? = null

			mouse.buttonDown.listen {
				when (it.button) {
					MouseButton.LEFT -> {
						val cand = screenToMap(mouse.position)
						println(cand)
						highlighted =
							if (gameState[cand] != null) {
								Pair(cand, gameState[cand]!!)
							} else {
								null
							}

						println(highlighted)
					}
					else -> {}
				}
			}

			extend {
				val corner = mouse.position.map { x: Double -> floor(x / 80.0) * 80.0 }
				drawer.strokeWeight = -1.0

				drawer.image(board)

				drawer.fill = ColourRGBa.MAGENTA.copy(alpha = 0.3)
				drawer.rectangle(corner, 80.0)

				drawer.fill = ColourRGBa.GREEN.copy(alpha = 0.3)
				if (highlighted != null) {
					val (pos, piece) = highlighted!!
					for ((dx, dy) in piece.type.moves()) {
						val hlCorner =
							mapToScreen(
								Pair(
									pos.first + dx,
									pos.second + dy,
								),
							)
						drawer.rectangle(hlCorner, 80.0)
					}
				}
				drawer.drawBoardState(gameState, pieces, w, h)
			}
		}
	}
