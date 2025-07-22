package homeMusic.Tracks;

public class SoundcloudTrack extends Track {

    public SoundcloudTrack(String url) {
        String[] urlInformation = extractUrlInformation(url);

        this.title = urlInformation[0];
        this.artist = urlInformation[1];
        this.url = url;
    }

    @Override
    public String[] extractUrlInformation(String url) {
        String[] information = new String[2];
        information[0] = "Track Title"; // Placeholder for title extraction logic
        information[1] = "Track Artist"; // Placeholder for artist extraction logic
        
        return information;
    }
}
