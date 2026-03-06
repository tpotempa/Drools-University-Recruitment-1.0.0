package pl.edu.atar.universityrecruitment;

import java.util.List;
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

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // Tworzenie kolekcji faktów
        List<Candidate> uc = new Dataset().getUniversityCandidates();

        // HOW-TO :: Uruchomienie przykładu.
        // Każdy przykład (Example 11 .. Example 71) należy uruchamiać niezależnie.
        // W celu uruchomienia określonego przykładu należy ustawić wartość zmiennej example.
        // Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.

        // Uruchamiany przykład
        int example = 11;

        KieServices kService = KieServices.Factory.get();
        KieContainer kContainer = kService.getKieClasspathContainer();

        // Baza wiedzy jest konfigurowana via /resources/META-INF/kmodule.xml.
        String knowledgeBaseName = "";
        switch (example) {

            // REGUŁY PODSTAWOWE
            case 11:
                // Set 1. Example 1.
                // OPIS: Uruchamianie zbiorów reguł z określoną agenda-group.
                // Agenda grupuje reguły w nazwane zbiory pozwalając sterować,
                // które zbiory reguł mają być podczas wnioskowania przetwarzane.
                // UWAGA: W celu wykonania testu niniejszego przykładu należy w zbiorze
                // reguł DoNotFocusAutomatically_OlympicQualification.drl sprawdzić czy jest
                // odkomentowany n/w wiersz:
                // agenda-group "do_not_focus_automatically"
                knowledgeBaseName = "basic";
                break;
            case 12:
                // Set 1. Example 2.
                // OPIS: Uruchamianie zbiorów reguł bez określonej agenda-group.
                // Agenda grupuje reguły w nazwane zbiory pozwalając sterować,
                // które zbiory reguł mają być podczas wnioskowania przetwarzane.
                // Reguły bez określonej agenda-group są niejawnie przyporządkowane
                // do domyślnej agendy 'DEFAULT' / 'MAIN'! Agenda domyślna jest
                // w procesie wnioskowanie ZAWSZE wywoływana tj. uzyskuje FOCUS.
                // Wywołanie jest niejawne i odbywa się po jawnych wywołaniach agend.
                // UWAGA: W celu wykonania testu niniejszego przykładu należy w zbiorze
                // reguł DoNotFocusAutomatically_OlympicQualification.drl zakomentować n/w wiersz:
                // agenda-group "do_not_focus_automatically"
                // Po przeprowadzeniu testów należy odkomentować w/w wiersz.
                knowledgeBaseName = "basic";
                break;

            // REGUŁY ELEMENTARNE
            case 21:
                // Set 2. Example 1.
                // OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
                // kwalifikacyjnych.
                knowledgeBaseName = "elementary.one_set_of_rules";
                break;
            case 22:
                // Set 2. Example 2.
                // OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
                // kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego
                // wnioskowania.
                // PYTANIE: W jakim celu używany jest counter?
                knowledgeBaseName = "elementary.one_set_of_rules_modify";
                break;
            case 23:
                // Set 2. Example 3.
                // OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
                // kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego
                // wnioskowania oraz klauzulą no-loop uniemożliwiającą ponowne uruchomienie reguły
                // przez samą siebie w przypadku ponownego wnioskowania
                knowledgeBaseName = "elementary.one_set_of_rules_no-loop_modify";
                break;

            // REGUŁY ŚREDNIOZAAWANSOWANE NIŻSZE
            case 31:
                // Set 3. Example 1.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                knowledgeBaseName = "intermediate.lower.two_sets_of_rules";
                break;
            case 32:
                // Set 3. Example 2.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Reguły sa uruchamiane z parametrem salience, który określa priorytet
                // kolejności uruchomienia (wyższa wartość oznacza wyższy priorytet).
                knowledgeBaseName = "intermediate.lower.two_sets_of_rules_salience";
                break;
            case 33:
                // Set 3. Example 3.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Uruchamianie tylko jednej reguły kwalifikacyjnej, gdyż nie jest zasadne
                // 2-krotna kwalifikacja kandydata, z wykorzystaniem parametru activation-group,
                // który określa, że gdy dowolna reguła należąca do activation-group zostanie aktywowana,
                // uruchomienie wszystkich pozostałych, które należą do grupy, jest anulowane.
                // PYTANIE: Dlaczego nie wszystie fakty tj. kandydaci mają określoną decyzję?
                knowledgeBaseName = "intermediate.lower.two_sets_of_rules_salience_activation-group";
                break;

            // REGUŁY ŚREDNIOZAAWANSOWANE ŚRODKOWE
            // UWAGA: Należy podkreślić, że kandydat będący finalistą olimpiady nie może zostać
            // przyjęty bezpośrednio na podstawie olimpiady. Jedyną podstawą kwalifikacji
            // kandydata jest liczba punktów egzaminacyjnych.
            case 41:
                // Set 4. Example 1.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Reguła ze zbioru "OlympicQualification" jest wykonywana z MODFIKACJĄ faktu
                // powodującą uruchomienie ponownego wnioskowania.
                // Modyfikacja polega na 2-krotnym zwiększeniu liczby punktów egzaminacyjnych,
                // uzyskanych przez kandydata będącego finalistą olimipiady.
                // Reguła "ExamQualification" jest także wykonywana z modyfikacją faktu.
                // Reguły posiadają parametry no-loop oraz salience.
                knowledgeBaseName = "intermediate.middle.two_sets_of_rules_salience_no-loop_modify";
                break;
            case 42:
                // Set 4. Example 2.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Reguła ze zbioru "OlympicQualification" jest wykonywana z MODFIKACJĄ faktu
                // powodującą uruchomienie ponownego wnioskowania.
                // Modyfikacja polega na 2-krotnym zwiększeniu liczby punktów egzaminacyjnych,
                // uzyskanych przez kandydata będącego finalistą olimipiady.
                // Reguła "ExamQualification" jest wykonywana nez modyfikacji faktu.
                // Reguły posiadają parametry no-loop oraz salience.
                knowledgeBaseName = "intermediate.middle.two_sets_of_rules_salience_no-loop_one_modify";
                break;
            case 43:
                // Set 4. Example 3.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Reguła ze zbioru "OlympicQualification" jest wykonywana z MODFIKACJĄ faktu
                // powodującą uruchomienie ponownego wnioskowania.
                // Modyfikacja polega na 2-krotnym zwiększeniu liczby punktów egzaminacyjnych,
                // uzyskanych przez kandydata będącego finalistą olimipiady.
                // Reguła "ExamQualification" jest także wykonywana z modyfikacją faktu.
                // Reguły posiadają parametry lock-on-active oraz salience.
                knowledgeBaseName = "intermediate.middle.two_sets_of_rules_salience_lock-on-active_modify";
                break;

            // REGUŁY ŚREDNIOZAAWANSOWANE WYŻSZE
            case 51:
                // Set 5. Example 1.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są
                // wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
                // Reguły posiadają parametry activation-group oraz salience.
                // PYTANIE: Dlaczego reguły zostały uruchomione 10-krotnie?
                knowledgeBaseName = "intermediate.upper.two_sets_of_rules_salience_activation-group_modify";
                break;
            case 52:
                // Set 5. Example 2.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są
                // wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
                // Reguły posiadają parametry activation-group, salience oraz no-loop.
                // PYTANIE: Dlaczego mimo użycia parametru no-loop reguły zostały uruchomione
                // 10-krotnie?
                knowledgeBaseName = "intermediate.upper.two_sets_of_rules_salience_activation-group_no-loop_modify";
                break;
            case 53:
                // Set 5. Example 3.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są
                // wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
                // Reguły posiadają parametry activation-group, salience oraz lock-on-active.
                // PYTANIE: Jak działa parametr lock-on-active?
                knowledgeBaseName = "intermediate.upper.two_sets_of_rules_salience_activation-group_lock-on-active_modify";
                break;

            // REGUŁY ZAAWANSOWANE
            case 61:
                // Set 6. Example 1.
                // OPIS: Uruchamianie 3 zbiorów reguł kwalifikacyjnych. Zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // PYTANIE: W jakiej kolejności wykonują się reguły?
                knowledgeBaseName = "knowledgeable";
                break;

            // REGUŁY MISTRZOWSKIE
            case 71:
                // Set 7. Example 1.
                // OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Zbiory reguł nie
                // wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
                // UWAGA: Dla prawidłowej analizy działania własności PropertyReactive
                // należy zmodyfikować klasę UniversityCandidate dodając wiersz kodu:
                // @org.kie.api.definition.type.PropertyReactive
                // PYTANIE: W jakiej kolejności wykonują się reguły?
                // Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.
                knowledgeBaseName = "masterful";
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

            LOGGER.info("STATELESS REASONINGS FOR CANDIDATE {} ({})", candidate.getId(), candidate);

            // REASONING LOGGING - Complete logging [RED LOGS]
            // kSession.addEventListener(new DebugAgendaEventListener());
            // kSession.addEventListener(new DebugRuleRuntimeEventListener());

            // REASONING LOGGING - Simplified logging [BLACK LOGS]
            KieLoggers loggers = kService.getLoggers();
            KieRuntimeLogger consoleLogger = loggers.newConsoleLogger(kSession);
            KieRuntimeLogger fileLogger = loggers.newFileLogger(kSession, "./logs/reasoning_fact_" + candidate.getId());

            // Wnioskowanie z użyciem przekazanego faktu, który jest dodawany do pamięci roboczej Working Memory.
            kSession.execute(candidate);

            LOGGER.info("Information AFTER reasoning (below):\n{}\n", candidate.getCandidateInformationLogger());
            consoleLogger.close();
            fileLogger.close();
        }
    }
}