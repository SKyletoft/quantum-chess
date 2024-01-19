build:
	./gradlew build

run-intel:
	nixGLIntel ./gradlew run

run:
	./gradlew run

.PHONY: build run
