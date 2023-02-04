import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ScuttleTests {
    @Test
    public void simpleAssert(){
        assertEquals(1, 1);
    }

    // @Test
	// public void badlogicLogoFileExists() {
    //     String path = "/home/kyle/vscode/s23-scuttlers/antscuttle/assets/badlogic.jpg";
    //     FileHandle file = Gdx.files.internal(path);
    //     if (file == null) {
    //       System.err.println("FileHandle is null, file does not exist");
    //       return;
    //     }
    //     assertTrue(file.exists());
	// }
}
