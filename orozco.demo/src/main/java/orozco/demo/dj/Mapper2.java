package orozco.demo.dj;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper2 extends Mapper<Object, Text, Text, Text> {
	private String fileTag = "s2~";

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		String[] tokens = value.toString().split("\t");
		context.write(new Text(tokens[0]), new Text(fileTag + tokens[1]));
	}
}
