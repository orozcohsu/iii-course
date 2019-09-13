package orozco.demo.pv;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WebDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "web analysis example");
		job.setJarByClass(WebDriver.class);
		job.setMapperClass(WebMapper.class);
		job.setCombinerClass(WebReducer.class);
		job.setReducerClass(WebReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);


		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.209.131:8020/data2"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.209.131:8020/output/e2"));

		if (!job.waitForCompletion(true))
			return;
	}

}
