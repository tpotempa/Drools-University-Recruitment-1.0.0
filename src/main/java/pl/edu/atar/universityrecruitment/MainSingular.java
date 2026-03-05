package pl.edu.atar.universityrecruitment;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.logger.KieLoggers;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;

public class MainSingular {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainSingular.class);

    public static void main(String[] args) {

        // Tworzenie kolekcji faktów.
        // Preferowane jest dodawanie do kolekcji TYLKO 1 faktu w celu zachowania czytelności
        // analizy procesu wnioskowania systemu.
        List<Candidate> uc = new ArrayList<Candidate>();
        // Dodanie pojedynczego faktu do kolekcji.
        uc.add(new Dataset().getUniversityCandidatesWithSubjects().get(0));

        // HOW-TO :: Uruchomienie przykładu.
        // Każdy przykład (Example 81 .. Example 92) należy uruchamiać niezależnie.
        // W celu uruchomienia określonego przykładu należy ustawić wartość zmiennej example.
        // Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.

        // Uruchamiany przykład
        int example = 81;

        KieServices kService = KieServices.Factory.get();
        KieContainer kContainer = kService.getKieClasspathContainer();

        // Baza wiedzy jest konfigurowana via /resources/META-INF/kmodule.xml.
        String knowledgeBaseName = "";
        switch (example) {

            // REGUŁY WIRTUOZOWSKIE NIEOBLIGATORYJNE
            case 81:
                // Set 8. Example 1.
                // OPIS: Uruchamianie 5 zbiorów reguł kwalifikacyjnych. Zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.
                knowledgeBaseName = "virtuoso.nobligatory";
                break;

            // REGUŁY WIRTUOZOWSKIE OBLIGATORYJNE
            case 91:
                // Set 9. Example 1.
                // ZADANIE DO IMPLEMENTACJI.
                // OPIS: ...
                // Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.
                knowledgeBaseName = "virtuoso.nobligatory.solutiona";
                break;
            case 92:
                // Set 9. Example 2.
                // ZADANIE DO IMPLEMENTACJI.
                // OPIS: ...
                // Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.
                knowledgeBaseName = "virtuoso.nobligatory.solutionb";
                break;
        }

        // Tworzenie bazy wiedzy tj. dodawanie zbioru reguł do pamięci produkcyjnej Production Memory.
        KieBase kBase = kContainer.getKieBase(knowledgeBaseName);
        LOGGER.info("Knowledge base created.\n");

        long loadedRuleCount = 0;
        LOGGER.info("Knowledge bases consists of following rule(s):");
        for (KiePackage kiePackage : kBase.getKiePackages()) {
            for (Rule rule : kiePackage.getRules()) {
                loadedRuleCount++;
                LOGGER.info("{} : {}", loadedRuleCount, rule.getName());
            }
        }
        LOGGER.info("Total number of rules loaded in memory: {}\n", loadedRuleCount);

        for (Candidate candidate : uc) {

            KieSessionConfiguration config = KieServices.get().newKieSessionConfiguration();
            StatelessKieSession kSession = kBase.newStatelessKieSession(config);

            LOGGER.info("STATELESS REASONINGS FOR CANDIDATE {} ({})\n\n{}\n\n{}", candidate.getId(), candidate, candidate.getCandidateInformationLogger(), candidate.getCandidateSubjectsInformation());

            // REASONING LOGGING - Complete logging [RED LOGS]
            // kSession.addEventListener(new DebugAgendaEventListener());
            // kSession.addEventListener(new DebugRuleRuntimeEventListener());

            // REASONING LOGGING - Simplified logging [BLACK LOGS]
            KieLoggers loggers = kService.getLoggers();
            KieRuntimeLogger consoleLogger = loggers.newConsoleLogger(kSession);
            KieRuntimeLogger fileLogger = loggers.newFileLogger(kSession, "./logs/reasoning_singular_fact_" + candidate.getId());

            // Wnioskowanie z użyciem przekazanego faktu, który jest dodawany do pamięci roboczej Working Memory.
            kSession.execute(candidate);

            LOGGER.info("Information AFTER reasoning (below):\n{}\n", candidate.getCandidateInformationLogger());
            consoleLogger.close();
            fileLogger.close();
        }
    }
}