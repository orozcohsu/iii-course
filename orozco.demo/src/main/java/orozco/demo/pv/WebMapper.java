package orozco.demo.pv;

import java.io.IOException;
import java.net.URL;
import java.security.PrivilegedExceptionAction;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.GenericOptionsParser;

public class WebMapper extends Mapper<Object, Text, Text, IntWritable> {
	
  private final static IntWritable one = new IntWritable(1);
  private Text word = new Text();
  public void map(Object key, Text value, Context context)
    throws IOException, InterruptedException {
  
	String[] tokens = value.toString().split("\t");
    URL aurl = new URL(tokens[1]);
    context.write(new Text(aurl.getHost()), one);
    }
}

