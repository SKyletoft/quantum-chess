import org.openrndr.application
import org.openrndr.draw.loadImage
import org.openrndr.drawImage

fun main() =
	application {
		configure {
			width = 640
			height = 640
		}

		program {
			val board = drawImage(drawer.context.width, drawer.context.height) { drawChessBoard(drawer) }
			val pieces = loadImage("data/images/pieces.png")
			val gameState = Board.startingPosition.copy()

			extend {
				val w = drawer.context.width.toDouble() / 8.0
				val h = drawer.context.height.toDouble() / 8.0

				drawer.image(board)
				drawer.drawBoardState(gameState, pieces, w, h)
			}
		}
	}
