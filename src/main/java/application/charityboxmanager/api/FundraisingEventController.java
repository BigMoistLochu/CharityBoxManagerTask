package application.charityboxmanager.api;


import application.charityboxmanager.api.dto.FundraisingEventDto;
import application.charityboxmanager.service.FundraisingEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
public class FundraisingEventController {

    private final FundraisingEventService eventService;

    public FundraisingEventController(FundraisingEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<FundraisingEventDto> createFundraisingEvent(@RequestBody String name) {
        FundraisingEventDto event = eventService.createFundraisingEvent(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @PutMapping("/{eventId}/box/{boxId}")
    public ResponseEntity<Void> assignCollectionBoxToEvent(@PathVariable Long eventId, @PathVariable Long boxId) {
        eventService.assignEventToCollectionBox(eventId, boxId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
