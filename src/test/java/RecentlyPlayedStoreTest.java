import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.LinkedList;

public class RecentlyPlayedStoreTest {
    RecentlyPlayedStore store;
    @BeforeClass
    public void setup() {
        store = new RecentlyPlayedStore(Constants.MAX_PLAYLIST_SIZE);
        // Add songs played
        for (int i = 1; i <= Constants.MAX_PLAYLIST_SIZE; i++) {
            store.addPlayedSong("user", "S"+i);
        }
    }
    @Test (priority = 1)
    public void validatePlaylistSize() {
        LinkedList<String> recentlyPlayedSongs = store.getRecentlyPlayedSongs("user");
        // Retrieve recently played songs
        System.out.println("Test 1: User Songs Playlist: " + recentlyPlayedSongs);

        // S4 song is played and added to Playlist
        store.addPlayedSong("user", "S" + (Constants.MAX_PLAYLIST_SIZE + 1));
        System.out.println("Test 1: User Songs Playlist post playing a new song: " + recentlyPlayedSongs);

        // Verify the Playlist Size.
        Assert.assertEquals("Test 1: Error - Songs in recently played song is less than Max Size and no of songs are" + recentlyPlayedSongs.size(),recentlyPlayedSongs.size(), Constants.MAX_PLAYLIST_SIZE);
        System.out.println("Test 1: Playlist size Verified and actual playlist size is equal to: "+Constants.MAX_PLAYLIST_SIZE);

        // Playlist songs order verification
        for (int i = 0; i < Constants.MAX_PLAYLIST_SIZE; i++) {
            Assert.assertEquals( "Test 1: Order of songs verification failed",recentlyPlayedSongs.get(i), "S" + (i + 2));
        }
        System.out.println("Test 1: Order of songs verified");
    }

    @Test (priority = 2)
    public void validatePlayListOrder() {
        LinkedList<String> recentlyPlayedSongs = store.getRecentlyPlayedSongs("user");
        // Retrieve recently played songs
        System.out.println("\nTest 2: User Songs Playlist: " + recentlyPlayedSongs);

        // S2 song is played
        store.addPlayedSong("user", "S" + (Constants.MAX_PLAYLIST_SIZE - 1));
        System.out.println("Test 2: User Songs Playlist post playing a new song: " + recentlyPlayedSongs);

        // Playlist songs order verification
        for (int i = 0; i < (Constants.MAX_PLAYLIST_SIZE - 1); i++) {
                Assert.assertEquals("Test 2: Order of songs verification failed", recentlyPlayedSongs.get(i), "S" + (i + 3));
        }
        System.out.println("Test 2: Order of songs verified");


        // Retrieve recently played songs
        System.out.println("\nTest 3: User Songs Playlist: " + recentlyPlayedSongs);

        // S1 song is played
        store.addPlayedSong("user", "S" + (Constants.MAX_PLAYLIST_SIZE - 2));
        System.out.println("Test 3: User Songs Playlist post playing a new song: " + recentlyPlayedSongs);

        // Playlist songs order verification
        for (int i = 0; i < (Constants.MAX_PLAYLIST_SIZE - 2); i++) {
            Assert.assertEquals("Test 3: Order of songs verification failed", recentlyPlayedSongs.get(i), "S" + (i + 4));
        }
        System.out.println("Test 3: Order of songs verified");
    }
}
