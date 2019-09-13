package orozco.demo.ub;

import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.security.UserGroupInformation;

public class UserDriver {

	public static void main(String[] args) {
		UserGroupInformation ugi = UserGroupInformation.createRemoteUser("root");
		try {
			ugi.doAs(new PrivilegedExceptionAction<Void>() {
				public Void run() throws Exception {
					Configuration conf = new Configuration();
					Job job = Job.getInstance(conf, "Customer Like Analysis Example");
					job.setJarByClass(UserDriver.class);
					job.setOutputKeyClass(Text.class);
					job.setOutputValueClass(FloatWritable.class);
					job.setMapperClass(StatMapper.class);
					job.setReducerClass(StatReducer.class);
					FileInputFormat.addInputPath(job, new Path(
							"hdfs://192.168.209.131:8020/data3/ratings.txt"));
					FileOutputFormat.setOutputPath(job, new Path(
							"hdfs://192.168.209.131:8020/output/e3"));
					job.waitForCompletion(true);
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
