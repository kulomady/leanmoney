package com.kulomady.data.cache;

import com.kulomady.data.ApplicationTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * Created by kulomady on 5/20/16.
 */
public class FileManagerTest extends ApplicationTestCase{

    private FileManager fileManager;
    private File cacheDir;

    @Before
    public void setUp() throws Exception {
        fileManager = new FileManager();
        cacheDir = RuntimeEnvironment.application.getCacheDir();
    }

    @After
    public void tearDown() throws Exception {
        if (cacheDir != null) {
            fileManager.clearDirectory(cacheDir);
        }

    }

    @Test
    public void shouldWriteToFile() throws Exception {
        File fileToWrite = createDummyFile();
        String fileContent = "content\n";

        fileManager.writeToFile(fileToWrite, fileContent);
        String expectedContent = fileManager.readFileContent(fileToWrite);

        assertThat(expectedContent, is(equalTo(fileContent)));
    }

    @Test
    public void shouldReadFileContent() throws Exception {
        File fileToWrite = createDummyFile();
        String fileContent = "content\n";

        fileManager.writeToFile(fileToWrite, fileContent);
        String expectedContent = fileManager.readFileContent(fileToWrite);

        assertThat(expectedContent, is(equalTo(fileContent)));
    }

    private File createDummyFile() {
        String dummyFilePath = cacheDir.getPath() + File.separator + "madyDumyFile";
        return new File(dummyFilePath);
    }


}