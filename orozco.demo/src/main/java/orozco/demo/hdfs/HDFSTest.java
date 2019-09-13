package orozco.demo.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSTest {
    
    static final String INPUT_PATH = "hdfs://192.168.209.131:8020/data/test";
    static final String OUTPUT_PATH = "hdfs://192.168.209.131:8020/out";
    
    public static void main(String[] args) throws IOException, URISyntaxException {
        Configuration conf = new Configuration();
        final FileSystem fileSystem = FileSystem.get(new URI(INPUT_PATH), conf);
        final Path outPath = new Path(OUTPUT_PATH);
        if (fileSystem.exists(outPath)) {
            fileSystem.delete(outPath, true);
        }
        
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(INPUT_PATH));
        fsDataOutputStream.writeBytes("welcome to here ...");
    }

}