
.PHONY: test
test:
	gradlew test

.PHONY: build
build:
	gradlew clean build


.PHONY: run
run: build
	java -jar ./build/libs/cribbage.jar
