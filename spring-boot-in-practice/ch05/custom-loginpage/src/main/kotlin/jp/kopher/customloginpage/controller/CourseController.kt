package jp.kopher.customloginpage.controller

import jakarta.validation.Valid
import jp.kopher.customloginpage.model.Course
import jp.kopher.customloginpage.service.CourseService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


@Controller
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping("/")
    fun index(): String {
        return "redirect:/index"
    }

    @GetMapping("/index")
    fun index(model: Model): String {
        val courses = courseService.findAllCourses().toList()
        model.addAttribute("courses", if (courses.isEmpty()) emptyList() else courses)
        return "index"
    }

    @GetMapping("/addcourse")
    fun showAddCourseForm(course: Course): String {
        return "add-course"
    }

    @PostMapping("/addcourse")
    fun addCourse(@Valid course: Course, result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            return "add-course"
        }

        courseService.createCourse(course)
        model.addAttribute("courses", courseService.findAllCourses())
        return "redirect:/index"
    }

    @GetMapping("/update/{id}")
    fun showUpdateCourseForm(@PathVariable id: Long, model: Model): String {
        model.addAttribute("course", courseService.findCourseById(id).get())
        return "update-course"
    }

    @PutMapping("/update/{id}")
    fun updateCourse(
        @PathVariable("id") id: Long?,
        course: @Valid Course?,
        result: BindingResult,
        model: Model
    ): String {
        if (result.hasErrors()) {
            course?.id = id
            return "update-course"
        }
        courseService.updateCourse(course!!)
        model.addAttribute("courses", courseService.findAllCourses())
        return "redirect:/index"
    }

    @DeleteMapping("/delete/{id}")
    fun deleteCourse(@PathVariable("id") id: Long?, model: Model): String {
        courseService.deleteCourseById(id!!)
        model.addAttribute("courses", courseService.findAllCourses())
        return "redirect:/index"
    }
}
