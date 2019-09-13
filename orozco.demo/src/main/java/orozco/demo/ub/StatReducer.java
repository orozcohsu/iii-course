package orozco.demo.ub;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StatReducer extends
		Reducer<Text, FloatWritable, Text, FloatWritable> {
	private FloatWritable result = new FloatWritable();

	public void reduce(Text key, Iterable<FloatWritable> values,
			Context context) throws IOException, InterruptedException {
		float sum = 0, count = 0;
		for (FloatWritable val : values) {
			sum += val.get();
			count += 1;
		}
		result.set(sum / count);
		context.write(key, result);
	}
}
