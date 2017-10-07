package spitter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spitter.Spittle;
import spitter.data.SpittleRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = GET)
    public String spittles(@RequestParam(value = "maxId", defaultValue = MAX_LONG_AS_STRING) long maxId,
                           @RequestParam(value = "count", defaultValue = "20") int count,
                           Model model) {
        final List<Spittle> spittles = spittleRepository.findSpittles(maxId, count);
        model.addAttribute("spittleList", spittles);
        return "spittles";
    }

    @RequestMapping(value = "/{spittleId}", method = GET)
    public String spittle(@PathVariable long spittleId, Model model) {
        model.addAttribute("spittle", spittleRepository.findOne(spittleId));
        return "spittle";
    }
}
