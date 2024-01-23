build:
	./gradlew build --warning-mode=none

run-intel:
	nixGLIntel ./gradlew run --warning-mode=none

run:
	./gradlew run --warning-mode=none

clean:
	./gradlew clean --warning-mode=none

.PHONY: build run
