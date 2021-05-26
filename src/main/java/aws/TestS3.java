package aws;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

public class TestS3 {


    private AmazonS3 createAmazonS3(String awsRegion) {
        ClientConfiguration config = new ClientConfiguration();

        AmazonS3ClientBuilder s3Builder = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(awsRegion));

        s3Builder.withCredentials(new InstanceProfileCredentialsProvider(false));

        return s3Builder.build();
    }

    private void showFile(String region, String bucketName, String key) throws Exception {
        AmazonS3 s3 = createAmazonS3(region);
        boolean exist = s3.doesObjectExist(bucketName, key);
        if (exist) {
            S3Object object = s3.getObject(bucketName, key);
            byte[] content = IOUtils.toByteArray(object.getObjectContent());
            System.out.println("content:" + new String(content));
        } else {
            System.out.println("content not exist.");
        }
    }

    public static void main(String[] args) throws Exception {
        TestS3 t = new TestS3();
        t.showFile("", "", "");
    }

}
