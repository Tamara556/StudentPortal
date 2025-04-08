package com.example.studentPortal;

import com.example.studentPortal.model.Lesson;
import com.example.studentPortal.model.Student;
import com.example.studentPortal.service.LessonService;
import com.example.studentPortal.service.StudentServise;

import java.util.List;
import java.util.Scanner;

public class StudentPortal {

    private static Scanner scanner = new Scanner(System.in);
    private static LessonService lessonService = new LessonService();
    private static StudentServise studentServise = new StudentServise();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addLesson();
                    break;
                case "2":
                    System.out.println(lessonService.getAllLessons());
                    break;
                case "3":
                    addStudent();
                    break;
                case "4":
                    System.out.println(studentServise.getAllStudents());
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void addStudent() {
        List<Lesson> allLessons = lessonService.getAllLessons();
        System.out.println("here are all lessons: ");
        for (Lesson lesson : allLessons) {
            System.out.println(lesson.getId() + " -> " + lesson.getName() + " -> " + lesson.getPrice());
        }
        System.out.println("Please input lesson id");
        int lessonId = Integer.parseInt(scanner.nextLine());
        Lesson lessonById = lessonService.getLessonById(lessonId);
        if (lessonById != null) {
            System.out.println("Please input student name, surname, email, phone");
            String studentDataStr = scanner.nextLine();
            String[] studentDataArr = studentDataStr.split(",");
            Student student = Student.builder()
                    .name(studentDataArr[0])
                    .surname(studentDataArr[1])
                    .email(studentDataArr[2])
                    .phone(studentDataArr[3])
                    .lesson(lessonById)
                    .build();
            studentServise.add(student);
            System.out.println("Student added");
        }
    }

    private static void addLesson() {
        System.out.println("Please input lesson name, price, lecturer name");
        String lessonDataStr = scanner.nextLine();
        String[] lessonDataArr = lessonDataStr.split(",");
        Lesson lesson = Lesson.builder()
                .name(lessonDataArr[0])
                .price(Double.parseDouble(lessonDataArr[1]))
                .lecturerName(lessonDataArr[2])
                .build();
        lessonService.add(lesson);
        System.out.println("Lesson added");
    }

    private static void printCommands() {
        System.out.println("Please input 0 for EXIT");
        System.out.println("Please input 1 for ADD_LESSON");
        System.out.println("Please input 2 for PRINT_ALL_LESSONS");
        System.out.println("Please input 3 for ADD_STUDENT");
        System.out.println("Please input 4 for PRINT_ALL_STUDENTS");
    }

}
