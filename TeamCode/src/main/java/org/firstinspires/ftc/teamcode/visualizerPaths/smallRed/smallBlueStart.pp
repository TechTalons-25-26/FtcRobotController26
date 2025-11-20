public static class Paths {


   public PathChain Path1;
   public PathChain Path2;
   public PathChain Path3;
   public PathChain Path4;


   public Paths(Follower follower) {
       Path1 = follower
               .pathBuilder()
               .addPath(new BezierLine(new Pose(90.000, 8.000), new Pose(90.000, 8.000)))
               .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(69))
               .build();


       Path2 = follower
               .pathBuilder()
               .addPath(new BezierLine(new Pose(90.000, 8.000), new Pose(90.000, 8.000)))
               .setLinearHeadingInterpolation(Math.toRadians(69), Math.toRadians(90))
               .build();


       Path3 = follower
               .pathBuilder()
               .addPath(new BezierLine(new Pose(90.000, 8.000), new Pose(72.000, 8.000)))
               .setTangentHeadingInterpolation()
               .build();


       Path4 = follower
               .pathBuilder()
               .addPath(
                       new BezierLine(new Pose(72.000, 8.000), new Pose(72.000, 30.000))
               )
               .setTangentHeadingInterpolation()
               .build();
   }
}
