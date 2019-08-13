package io.pivotal.pal.tracker;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long, TimeEntry> timeEntries = new ConcurrentHashMap<Long, TimeEntry>();

    private AtomicLong idProvider = new AtomicLong(0);

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(idProvider.incrementAndGet());
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }


    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if(timeEntries.containsKey(id) == false) {
            return null;
        }
        timeEntry.setId(id);
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public void delete(Long id) {
        timeEntries.remove(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>( timeEntries.values());
    }
}
