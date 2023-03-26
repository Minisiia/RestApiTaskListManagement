package genshin_tasks.controllers;

import genshin_tasks.dto.TaskDTO;
import genshin_tasks.models.Task;
import genshin_tasks.services.TasksService;
import genshin_tasks.util.TaskErrorResponse;
import genshin_tasks.util.TaskNotCreatedException;
import genshin_tasks.util.TaskNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TasksService tasksService;
    private final ModelMapper modelMapper;

    @Autowired
    public TasksController(TasksService tasksService,
                           ModelMapper modelMapper) {
        this.tasksService = tasksService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<TaskDTO> getTasks() {
        return tasksService.findAll().stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable("id") int id) {
        return convertToTaskDTO(tasksService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid TaskDTO taskDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new TaskNotCreatedException(errorMessage.toString());

        }
        tasksService.save(convertToTask(taskDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTask(@PathVariable("id") int id,
                                                 @RequestBody @Valid TaskDTO taskDTO,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new TaskNotCreatedException(errorMessage.toString());

        }
        tasksService.update(id, convertToTask(taskDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") int id) {
        tasksService.delete(id);
    }

    private Task convertToTask(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }

    private TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException e) {
        TaskErrorResponse response = new TaskErrorResponse(
                "Task with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handleException(TaskNotCreatedException e) {
        TaskErrorResponse response = new TaskErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
