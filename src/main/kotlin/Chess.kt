import org.openrndr.application
import org.openrndr.draw.Drawer
import org.openrndr.draw.loadImage
import org.openrndr.drawImage
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import org.openrndr.color.ColorRGBa as ColourRGBa

val drawChessBoard: Drawer.() -> Unit = {
	this.clear(ColourRGBa.fromHex(0xECD3B9))
	this.fill = ColourRGBa.fromHex(0xA16F5A)
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
	this.strokeWeight = 1.0
}

fun getSprite(
	x: Int,
	y: Int,
): Rectangle {
	val spriteSize = 334.0
	return Rectangle(
		x * spriteSize,
		y * spriteSize,
		spriteSize,
		spriteSize,
	)
}

val whiteKing   = getSprite(0, 0)
val whiteQueen  = getSprite(1, 0)
val whiteBishop = getSprite(2, 0)
val whiteKnight = getSprite(3, 0)
val whiteCastle = getSprite(4, 0)
val whitePawn   = getSprite(5, 0)
val blackKing   = getSprite(0, 1)
val blackQueen  = getSprite(1, 1)
val blackBishop = getSprite(2, 1)
val blackKnight = getSprite(3, 1)
val blackCastle = getSprite(4, 1)
val blackPawn   = getSprite(5, 1)

fun main() =
	application {
		configure {
			width = 640
			height = 640
		}

		program {
			val board = drawImage(drawer.context.width, drawer.context.height) { drawChessBoard(drawer) }
			val pieces = loadImage("data/images/pieces.png")

			extend {
				val w = drawer.context.width.toDouble() / 8.0
				val h = drawer.context.height.toDouble() / 8.0
				drawer.image(board)

				drawer.drawStyle.strokeWeight = 1.0
				drawer.fill = ColourRGBa.BLACK
				drawer.image(pieces, whiteKing, Rectangle(0.0, 0.0, w, h))
				drawer.image(pieces, whiteQueen, Rectangle(160.0, 0.0, w, h))
				drawer.image(pieces, blackKing, Rectangle(80.0, 0.0, w, h))
			}
		}
	}
