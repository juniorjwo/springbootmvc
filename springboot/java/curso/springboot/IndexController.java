package curso.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //mesmo papel da servlet em jsp, vai interceptar requisioces de uma url
public class IndexController {
@RequestMapping("/")
	public String index() {
		return "index";
	}
}
