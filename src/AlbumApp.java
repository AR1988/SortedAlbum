import dao.Album;
import dao.AlbumImpl;
import model.Photo;

import java.time.LocalDateTime;

/**
 * @author Andrej Reutow
 * created on 07.11.2023
 */
public class AlbumApp {

    public static void main(String[] args) {
        AlbumImpl album = new AlbumImpl(7);
        Photo[] ph = new Photo[6];
        LocalDateTime now = LocalDateTime.now();
        ph[0] = new Photo(1, 1, "title1", "url1", now.minusDays(7));
        ph[1] = new Photo(1, 2, "title2", "url2", now.minusDays(7));
        ph[2] = new Photo(1, 3, "title3", "url3", now.minusDays(5));
//        ph[3] = new Photo(2, 1, "title1", "url1", now.minusDays(7));
//        ph[4] = new Photo(2, 4, "title4", "url4", now.minusDays(2));
//        ph[5] = new Photo(1, 4, "title4", "url1", now.minusDays(2));
        for (int i = 0; i < ph.length; i++) {
            album.addPhoto(ph[i]);
        }

        for (Photo albumPhoto : album) {
            System.out.println(albumPhoto);
        }
    }
}
