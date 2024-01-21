import org.openrndr.application
import org.openrndr.draw.Drawer
import org.openrndr.draw.loadImage
import org.openrndr.drawImage
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import org.openrndr.color.ColorRGBa as ColourRGBa
import org.openrndr.draw.ColorBuffer as ColourBuffer

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