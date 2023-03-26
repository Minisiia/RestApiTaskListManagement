package genshin_tasks;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Необходимо создать RESTful сервис для управления списком задач.
 * Каждая задача должна содержать название и описание.
 * Для хранения задач необходимо использовать базу данных MySQL.
 * API должен содержать следующие эндпоинты:
 * GET /tasks - получение списка всех задач
 * GET /tasks/{id} - получение задачи по идентификатору
 * POST /tasks - создание новой задачи
 * PUT /tasks/{id} - обновление задачи по идентификатору
 * DELETE /tasks/{id} - удаление задачи по идентификатору
 * Для создания RESTful сервиса необходимо использовать Spring Boot, а для работы с базой данных - Spring Data JPA.
 * Конфигурация базы данных должна быть задана в файле application.properties.
 */
@SpringBootApplication
public class FirstRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstRestAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
