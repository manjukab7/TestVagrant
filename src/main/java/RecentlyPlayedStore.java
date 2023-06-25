import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class RecentlyPlayedStore {
    private final int capacity;
    private final Map<String, LinkedList<String>> recentlyPlayed;
    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.recentlyPlayed = new HashMap<>();
    }
    public void addPlayedSong(String user, String song) {
        LinkedList<String> songs = recentlyPlayed.getOrDefault(user, new LinkedList<>());
        // Add the song to the user's recently played list
        songs.addLast(song);
        // Check if the recently played list exceeds the capacity
        if (songs.size() > capacity) {
            // If it exceeds, remove the least recently played song
            songs.removeFirst();
        }
        recentlyPlayed.put(user, songs);
    }
    public LinkedList<String> getRecentlyPlayedSongs(String user) {
        // Return the recently played songs for the given user
        return recentlyPlayed.getOrDefault(user, new LinkedList<>());
    }
}
