package sadh.priya.organizr.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

	private final RunRepository runRepository;
	
	public RunController(RunRepository runRepository) {
		this.runRepository = runRepository;
	}
	
	@GetMapping("")
	List<Run> findAll() {
		return runRepository.findAll();
	}
	
	@GetMapping("/{id}")
	Run findById(@PathVariable Integer id) {
		Optional<Run> run = runRepository.findById(id);
		
		if(run.isEmpty()) {
			throw new RunNotFoundException();
		}
		
		return run.get();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	void create(@Valid @RequestBody Run run) {
		runRepository.create(run);
	}
	
	@PutMapping("/{id}")
	void update(@PathVariable Integer id, @Valid @RequestBody Run run) {
		runRepository.update(run, id);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable Integer id) {
		runRepository.delete(id);
	}
}
