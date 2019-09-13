package orozco.demo.dj;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JoinDriver {

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		Configuration conf = new Configuration();
		Job job2 = Job.getInstance(conf, "Customer Like Analysis Example2");
		job2.setJarByClass(JoinDriver.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);
		job2.setReducerClass(JoinReducer.class);
		
		MultipleInputs.addInputPath(job2, new Path(
				"hdfs://192.168.209.131:8020/data4/items.txt"), 
				TextInputFormat.class, Mapper1.class);
		MultipleInputs.addInputPath(job2, new Path(
				"hdfs://192.168.209.131:8020/output/e3"),
				TextInputFormat.class, Mapper2.class);
		FileOutputFormat.setOutputPath(job2, new Path(
				"hdfs://192.168.209.131:8020/output/e4"));
		System.exit(job2.waitForCompletion(true) ? 0 : 1);

	}

}
