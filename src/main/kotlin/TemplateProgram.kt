import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.drawImage
import org.openrndr.math.Vector2

val drawChessBoard: Drawer.() -> Unit = {
	this.clear(ColorRGBa.fromHex(0xECD3B9))
	this.fill = ColorRGBa.fromHex(0xA16F5A)
	val squares =
		listOf(
			Vector2(0.0, 0.0),
			Vector2(2.0, 0.0),
			Vector2(4.0, 0.0),
			Vector2(6.0, 0.0),
			Vector2(1.0, 1.0),
			Vector2(3.0, 1.0),
			Vector2(5.0, 1.0),
			Vector2(7.0, 1.0),
			Vector2(0.0, 2.0),
			Vector2(2.0, 2.0),
			Vector2(4.0, 2.0),
			Vector2(6.0, 2.0),
			Vector2(1.0, 3.0),
			Vector2(3.0, 3.0),
			Vector2(5.0, 3.0),
			Vector2(7.0, 3.0),
			Vector2(0.0, 4.0),
			Vector2(2.0, 4.0),
			Vector2(4.0, 4.0),
			Vector2(6.0, 4.0),
			Vector2(1.0, 5.0),
			Vector2(3.0, 5.0),
			Vector2(5.0, 5.0),
			Vector2(7.0, 5.0),
			Vector2(0.0, 6.0),
			Vector2(2.0, 6.0),
			Vector2(4.0, 6.0),
			Vector2(6.0, 6.0),
			Vector2(1.0, 7.0),
			Vector2(3.0, 7.0),
			Vector2(5.0, 7.0),
			Vector2(7.0, 7.0),
		).map {
			Vector2(
				it.x * this.context.width.toDouble() / 8,
				it.y * this.context.height.toDouble() / 8,
			)
		}

	// Beacuse apparently 0 still draws an outline?
	this.strokeWeight = -1.0
	this.rectangles(squares, this.context.width.toDouble() / 8, this.context.height.toDouble() / 8)
}

fun main() =
	application {
		configure {
			width = 640
			height = 640
		}

		program {
			val board = drawImage(drawer.context.width, drawer.context.height) { drawChessBoard(drawer) }

			extend {
				drawer.image(board)
			}
		}
	}
