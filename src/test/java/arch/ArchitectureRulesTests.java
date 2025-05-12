package arch;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.wallet")
public class ArchitectureRulesTests {

    @ArchTest
    void adapterClassesShouldOnlyBeUsedWithinAdapterOrTestPackages(JavaClasses classes) {
        ArchRule rule = noClasses()
                .that().resideOutsideOfPackage("com.wallet.adapter..")
                .and().resideOutsideOfPackage("com.wallet.test..")
                .and().resideOutsideOfPackage("com.wallet.mock..")
                .should().dependOnClassesThat()
                .resideInAPackage("com.wallet.adapter..")
                .as("Adapter classes should only be used within adapter or test-related packages");

        rule.check(classes);
    }

    @ArchTest
    void portOutInterfacesShouldHaveImplementationInAdapter(JavaClasses classes) {
        ArchCondition<JavaClass> haveImplementationInAdapter =
                new ArchCondition<>("have implementation in adapter package") {
                    @Override
                    public void check(JavaClass item, ConditionEvents events) {
                        boolean hasImplementation = classes.stream()
                                .filter(impl -> impl.getPackageName().matches(".*\\.adapter(\\..*)?"))
                                .anyMatch(impl -> impl.getInterfaces().contains(item));

                        if (!hasImplementation) {
                            events.add(SimpleConditionEvent.violated(item,
                                    item.getFullName() + " does not have an implementation in adapter package"));
                        }
                    }
                };

        ArchRule rule = classes()
                .that().resideInAPackage("..port.out..")
                .and().areInterfaces()
                .should(haveImplementationInAdapter)
                .as("Each output port should have at least one adapter implementation");

        rule.check(classes);
    }

    @ArchTest
    void portInInterfacesShouldHaveImplementationInService(JavaClasses classes) {
        ArchCondition<JavaClass> haveImplementationInService =
                new ArchCondition<>("have implementation in service package") {
                    @Override
                    public void check(JavaClass item, ConditionEvents events) {
                        boolean hasImplementation = classes.stream()
                                .filter(impl -> impl.getPackageName().matches(".*\\.service(\\..*)?"))
                                .anyMatch(impl -> impl.getInterfaces().contains(item));

                        if (!hasImplementation) {
                            events.add(SimpleConditionEvent.violated(item,
                                    item.getFullName() + " does not have an implementation in service package"));
                        }
                    }
                };

        ArchRule rule = classes()
                .that().resideInAPackage("..port.in..")
                .and().areInterfaces()
                .should(haveImplementationInService);

        rule.check(classes);
    }
}
