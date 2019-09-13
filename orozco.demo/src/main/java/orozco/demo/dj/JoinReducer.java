package orozco.demo.dj;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer<Text, Text, Text, Text> {
	private String status1, status2 = null;

	public void reduce(Text key, Iterable<Text> values, Context output)
			throws IOException, InterruptedException {
		ArrayList<String> ary = new ArrayList<String>();
		for (Text val : values) {
			String splitVals[] = val.toString().split("~");
			if (splitVals[0].equals("s1")) {
				status1 = splitVals[1] != null ? splitVals[1].trim() : "status1";
			} else if (splitVals[0].equals("s2")) {
				status2 = splitVals[1] != null ? splitVals[1].trim() : "status2";
				ary.add(status2);
			}
		}
		for (String meanrating : ary)
			output.write(key, new Text(status1 + "\t" + meanrating));
	}
}
