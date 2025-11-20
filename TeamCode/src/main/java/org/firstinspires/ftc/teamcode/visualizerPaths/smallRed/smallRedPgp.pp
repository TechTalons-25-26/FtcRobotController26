public static class Paths3 {


   public PathChain Path5;
   public PathChain Path2;
   public PathChain Path3;


   public Paths(Follower follower) {
       Path5 = follower
               .pathBuilder()
               .addPath(
                       new BezierLine(new Pose(72.000, 30.000), new Pose(72.000, 30.000))
               )
               .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(260))
               .build();


       Path2 = follower
               .pathBuilder()
               .addPath(
                       new BezierCurve(
                               new Pose(72.000, 30.000),
                               new Pose(77.239, 54.847),
                               new Pose(88.332, 59.367),
                               new Pose(129.416, 59.777)
                       )
               )
               .setTangentHeadingInterpolation()
               .setReversed(true)
               .build();


       Path3 = follower
               .pathBuilder()
               .addPath(
                       new BezierCurve(
                               new Pose(129.416, 59.777),
                               new Pose(96.343, 63.270),
                               new Pose(73.336, 72.719),
                               new Pose(83.813, 83.606)
                       )
               )
               .setTangentHeadingInterpolation()
               .build();
   }
}


