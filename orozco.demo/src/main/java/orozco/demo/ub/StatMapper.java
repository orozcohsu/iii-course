package orozco.demo.ub;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StatMapper extends
		Mapper<Object, Text, Text, FloatWritable> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] tokens = value.toString().split("::");
		context.write(new Text(tokens[1]),
				new FloatWritable(Float.parseFloat(tokens[2])));
	}
}
