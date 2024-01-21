build:
	./gradlew build

run-intel:
	nixGLIntel ./gradlew run

run:
	./gradlew run

clean:
	./gradlew clean

.PHONY: build run
