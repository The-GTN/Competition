EXEC = examples
TEST = java -jar lib/test-1.7.jar

all: $(EXEC)

doc:
	cd src;javadoc -subpackages competition_sportive io util -d ../docs;

classes:
	rm -rf classes;mkdir classes

comp: classes
	cd src;javac */*.java */*/*.java -d ../classes;

compTest: comp
	javac -classpath lib/test-1.7.jar test/*/*/*.java;

test: compTest
	$(TEST) competition_sportive.competition.TestChampionnat
	$(TEST) competition_sportive.competition.TestTournoi
	$(TEST) competition_sportive.competition.TestMaster
	$(TEST) competition_sportive.competitor.TestCompetitor
	$(TEST) competition_sportive.match.TestMockMatch
	$(TEST) competition_sportive.match.TestRandomMatch


competition:
	java -jar dist/Competition.jar ${args}

examples:
	java -jar dist/Rendu.jar

exe: comp
	cd classes;jar cvfm ../dist/Competition.jar ../lib/manifest/manifest-Competition competition_sportive io util;
	cd classes;jar cvfm ../dist/Rendu.jar ../lib/manifest/manifest-Rendu competition_sportive io util;
	make clean;

extract:
	cd dist;jar xvf Competition.jar

clean:
	rm -rf classes docs test/*.class test/*/*.class test/*/*/*.class dist/META-INF dist/competition_sportive dist/util dist/io dist/observer dist/lib

.PHONY: clean extract exe test compTest comp classes doc all competition
