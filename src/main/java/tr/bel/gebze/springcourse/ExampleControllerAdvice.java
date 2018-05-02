package tr.bel.gebze.springcourse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@ControllerAdvice
@Slf4j
public class ExampleControllerAdvice {

	private final String sampleText;

	public ExampleControllerAdvice(@Value("${app.sample-text}") String sampleText) {
		this.sampleText = sampleText;
	}

	@ModelAttribute
	void addSampleText(Model model) {
		model.addAttribute("sampleText", sampleText);
	}

//	@ExceptionHandler({Exception.class})
//	String genericExceptionHandler(Exception e) {
//		log.error("There is an unknown exception with details: " + e.getMessage(), e);
//		return "generic-error";
//	}

}
