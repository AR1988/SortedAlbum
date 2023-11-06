package test;

import model.Photo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Andrej Reutow
 * created on 06.11.2023
 */
class PhotoTest {

    private final Photo photo1_1 = new Photo(1, 1, null, null, null);
    private final Photo photo1_2 = new Photo(1, 2, null, null, null);
    private final Photo photo2_1 = new Photo(2, 1, null, null, null);

    @Test
    public void test_compare() {


        int resultCompare1 = photo1_1.compareTo(photo1_2);
        Assertions.assertTrue(resultCompare1 < 0);

        int resultCompare2 = photo1_1.compareTo(photo2_1);
        Assertions.assertTrue(resultCompare2 < 0);
    }


    @Test
    public void test_sortArray() {
        Photo[] photos = new Photo[3];
        photos[0] = photo2_1;
        photos[1] = photo1_1;
        photos[2] = photo1_2;

        Arrays.sort(photos);

        Assertions.assertArrayEquals(new Photo[]{photo1_1, photo1_2, photo2_1}, photos);
    }
}
