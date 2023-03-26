package TemplateMethod;


abstract class Generalization {
    void findSolution() {
        stepOne();
        stepTwo();
        stepThr();
        stepFor();
    }
    private void stepOne() {
        System.out.println("Generalization.stepOne");
    }
    abstract void stepTwo();
    abstract void stepThr();

    void stepFor() {
        System.out.println( "Generalization.stepFor" );
    }
}

abstract class Specialization extends Generalization {
    protected void stepThr() {
        step3_1();
        step3_2();
        step3_3();
    }
    private void step3_1() {
        System.out.println("Specialization.step3_1");
    }
    abstract protected void step3_2();

    private void step3_3() {
        System.out.println("Specialization.step3_3");
    }
}

class Realization extends Specialization {
    protected void stepTwo() {
        System.out.println("Realization.stepTwo");
    }

    protected void step3_2() {
        System.out.println( "Realization.step3_2");
    }

    protected void stepFor() {
        System.out.println("Realization.stepFor");
        super.stepFor();
    }
}

public class TemplateMethodSimple {
    public static void main(String[] args) {
        Generalization algorithm = new Realization();
        algorithm.findSolution();
    }
}
