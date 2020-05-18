package com.example.sercuritydemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {

  @Test
  public void testBCrypt() {
    System.out.println("********************");
    String hashpw = BCrypt.hashpw("12138", BCrypt.gensalt());
    System.out.println(hashpw);
    boolean checkpw = BCrypt.checkpw("a", hashpw);
    System.out.println(checkpw);

  }
}
