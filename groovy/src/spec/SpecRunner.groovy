package spec;

import junit.framework.AssertionFailedError

import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.manipulation.Filter
import org.junit.runner.manipulation.Filterable
import org.junit.runner.manipulation.NoTestsRemainException
import org.junit.runner.manipulation.Sortable
import org.junit.runner.manipulation.Sorter
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier

public class SpecRunner extends Runner implements Filterable, Sortable {

  Class<?> testClass;
  Description root;

  SpecRunner(Class<?> testClass) {
    this.testClass = testClass;
  }

  @Override
  Description getDescription() {
    root = Description.createSuiteDescription(SpecRunner)
    testClass.specFiles().each {
      suiteClass -> 
      def childSuite = Description.createSuiteDescription(suiteClass)
      root.addChild(childSuite)
    }
    root
  }

  @Override
  void run(RunNotifier notifier) {
    root.children.each { child ->
      runSpecSuite(child) { name, code ->
        def test = Description.createTestDescription(child.testClass, name)
        notifier.fireTestStarted(test)
        try {
          code()
          notifier.fireTestFinished(test)
        } catch (Throwable e) {
          notifier.fireTestFailure(new Failure(test, e))
        } 
      }
    }
  }
  
  def runSpecSuite(specSuiteDescription, block) {
    Script script = specSuiteDescription.testClass.newInstance()
    script.binding =  new Binding(especificacion:block)
    script.run()
  }

  @Override
  void filter(Filter filter) throws NoTestsRemainException {
    // TODO Auto-generated method stub

  }

  @Override
  void sort(Sorter sorter) {
    // TODO Auto-generated method stub

  }
  

}