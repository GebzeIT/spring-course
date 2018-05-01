package tr.bel.gebze.springcourse;

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
public class ExampleControllerAdvice {

	private final String sampleText;

	public ExampleControllerAdvice(@Value("${app.sample-text}") String sampleText) {
		this.sampleText = sampleText;
	}

	@ModelAttribute
	void addSampleText(Model model) {
		model.addAttribute("sampleText", sampleText);
	}

}
