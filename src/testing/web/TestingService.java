package testing.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestingService extends Activity {
    /** Called when the activity is first created. */
    public EditText edit;
    public TextView text;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button)findViewById(R.id.button);
        edit = (EditText)findViewById(R.id.EditText);
        text =(TextView)findViewById(R.id.TextView);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fetch();
			}
		});
    }

	protected void fetch() {
		// TODO Auto-generated method stub
		String editPort = edit.getText().toString();
		//String url = "http://202.57.7.94:9258/SetPower.cgi?user=admin+pass=admin+p1=1";
//		String url = "http://202.57.7.94:9258/SetPower.cgi?";
	String url = "http://10.0.2.2/webupload/coba.php";
		ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
		post.add(new BasicNameValuePair("user", "admin"));
		post.add(new BasicNameValuePair("pass", "admin"));

		String param = URLEncodedUtils.format(post, "utf-8");
	//	url+=param;

	/*	String respone = null;
		try{
			String param = URLEncodedUtils.format(post, "utf-8");
		//	url+=param;
	//		respone = CustomHttpClient.executeHttpPost(url, post);
			Toast.makeText(TestingService.this, url, Toast.LENGTH_LONG).show();
			respone= CustomHttpClient.executeHttpGet(url);
			String res = respone.toString();
            
            res = res.trim();
             
            res = res.replaceAll("\\s+","");
            Toast.makeText(TestingService.this, res, Toast.LENGTH_LONG).show();
		}catch (Exception e) {
            
            e.printStackTrace();
             
         }
		*/
		
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpRequestBase httprequest = null;
		HttpResponse httpresponse = null;
		InputStream inputstream = null;
		String response ="";
		StringBuffer buffer = new StringBuffer();
		httprequest = new HttpGet(url);
		Toast.makeText(TestingService.this, url, Toast.LENGTH_LONG).show();
		
		try{
			httpresponse = httpclient.execute(httprequest);
		}catch(ClientProtocolException e1)
		{
			e1.printStackTrace();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		try{
			inputstream = httpresponse.getEntity().getContent();
			
		}catch(IllegalStateException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		byte[] data = new byte[512];
		int len =0;
		try{
			while(-1 !=(len = inputstream.read(data)))
			{
				buffer.append(new String(data, 0, len));
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		try{
			inputstream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		response = buffer.toString();
		text.setText(response);
	}
	}
