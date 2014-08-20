package org.tokiru.test;

import org.tokiru.test.creature.*;
import org.tokiru.test.creature.neutral.DarkIronDwarfTest;
import org.tokiru.test.creature.neutral.DefenderOfArgusTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class TestRunner {
    public static void main(String[] args) {
        List<Test> tests = new ArrayList<>();
        tests.add(new MinionTest());
        tests.add(new TauntTest());
        tests.add(new DivineShieldTest());
        tests.add(new ChargeTest());
        tests.add(new WindFurryTest());
        tests.add(new DarkIronDwarfTest());
        tests.add(new DefenderOfArgusTest());

        int passCount = 0;
        int failCount = 0;

        List<String> failedChecks = new ArrayList<>();

        for (Test test : tests) {
            test.init();
            System.out.println("Running " + test.getName());
            if (test.run()) {
                System.out.println("PASSED");
                passCount++;
            } else {
                System.out.println("FAILED");
                failCount++;
            }

            System.out.println("================");
        }

        System.out.println("================");
        System.out.println("Summary: passed " + passCount + " failed " + failCount);
        System.out.println("================");
    }
}
