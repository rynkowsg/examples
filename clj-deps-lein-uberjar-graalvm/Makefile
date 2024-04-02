.PHONY: run-lein uberjar run-uberjar

# No compilation
# Having a Leiningen or deps.edn project, you can run the main without compilation

run-lein:
	lein run

run-clj:
	clj -M -m example.main

# Uberjar
# Another option is to compile the project to an uberjar and run it

uberjar:
	lein with-profiles "+nonnative" classpath | tr ':' '\n' | sort
	@echo
	lein with-profiles "+nonnative" deps :tree
	@echo
	lein with-profiles "+nonnative" do clean, uberjar

run-uberjar:
	java -jar target/example-standalone.jar

# GraalVM Native Image
# Yet another, is to build a native image with GraalVM and run binary it produces

native:
	lein with-profiles "+native" classpath | tr ':' '\n' | sort
	@echo
	lein with-profiles "+native" deps :tree
	@echo
	lein with-profiles "+native" do clean, uberjar
	./scripts/compile-native.bash target/example-standalone.jar target/example-standalone

run-native:
	./target/example-standalone
