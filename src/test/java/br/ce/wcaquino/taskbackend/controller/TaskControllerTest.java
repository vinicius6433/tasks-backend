package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {
    @Mock
    private TaskRepo taskRepo;
    @InjectMocks
    private TaskController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void naoSalvarTarefasSemDescricao(){
        Task task = new Task();
//        task.setTask("Descrição");
        task.setDueDate(LocalDate.now());
        try {
            controller.save(task);
            Assert.fail("Não deveria chegar nesse ponto!");

        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description",e.getMessage());
        }

    }

    @Test
    public void naoSalvarTarefasSemData(){
        Task task = new Task();
        task.setTask("Descrição");
//        task.setDueDate(LocalDate.now());
        try {
            controller.save(task);
            Assert.fail("Não deveria chegar nesse ponto!");

        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date",e.getMessage());
        }
    }
    @Test
    public void naoSalvarTarefaComDataPassada(){
        Task task = new Task();
        task.setTask("Descrição");
        task.setDueDate(LocalDate.of(2001,01,12));
        try {
            controller.save(task);
            Assert.fail("Não deveria chegar nesse ponto!");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past",e.getMessage());
        }
    }
    @Test
    public void deveSalvarTarefaComSucesso() throws ValidationException {
        Task task = new Task();
        task.setTask("Descrição");
        task.setDueDate(LocalDate.now());
        controller.save(task);
        Mockito.verify(taskRepo).save(task);

    }

}
