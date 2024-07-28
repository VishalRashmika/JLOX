main:
	javac -d . --release 21 --enable-preview *.java

run:
	java lox.Lox

clean:
	rm *.class