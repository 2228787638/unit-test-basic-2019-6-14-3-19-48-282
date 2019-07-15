package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.rmi.UnexpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project firstProject =new Project(ProjectType.INTERNAL,"firstProject");
        ExpenseService expenseService =new ExpenseService();
        // when
        ExpenseType e=expenseService.getExpenseCodeByProjectTypeAndName(firstProject);
        // then
        Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE,e);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project firstProject =new Project(ProjectType.EXTERNAL,"Project A");
        ExpenseService expenseService =new ExpenseService();
        // when
        ExpenseType e=expenseService.getExpenseCodeByProjectTypeAndName(firstProject);
        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A,e);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project firstProject =new Project(ProjectType.EXTERNAL,"Project B");
        ExpenseService expenseService =new ExpenseService();
        // when
        ExpenseType e=expenseService.getExpenseCodeByProjectTypeAndName(firstProject);
        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_B,e);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project firstProject =new Project(ProjectType.EXTERNAL,"Project S");
        ExpenseService expenseService =new ExpenseService();
        // when
        ExpenseType e=expenseService.getExpenseCodeByProjectTypeAndName(firstProject);
        // then
        Assertions.assertEquals(ExpenseType.OTHER_EXPENSE,e);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project firstProject =new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"Project S");
        ExpenseService expenseService =new ExpenseService();
        // when
        // then
        Assertions.assertThrows(UnexpectedProjectTypeException.class,()->expenseService.getExpenseCodeByProjectTypeAndName(firstProject));
    }
}