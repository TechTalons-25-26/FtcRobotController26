public static class Paths 2{


   public PathChain Path2;
   public PathChain Path2;
   public PathChain Path3;
   public PathChain Path4;
   public PathChain Path5;


   public Paths(Follower follower) {
       Path2 = follower
               .pathBuilder()
               .addPath(
                       new BezierLine(new Pose(72.000, 30.000), new Pose(72.000, 30.000))
               )
               .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(200))
               .setReversed(true)
               .build();


       Path2 = follower
               .pathBuilder()
               .addPath(
                       new BezierCurve(
                               new Pose(72.000, 30.000),
                               new Pose(78.329, 33.184),
                               new Pose(86.154, 34.625),
                               new Pose(129.396, 35.861)
                       )
               )
               .setTangentHeadingInterpolation()
               .setReversed(true)
               .build();


       Path3 = follower
               .pathBuilder()
               .addPath(
                       new BezierCurve(
                               new Pose(129.396, 35.861),
                               new Pose(78.329, 33.218),
                               new Pose(86.154, 34.660),
                               new Pose(71.946, 29.889)
                       )
               )
               .setTangentHeadingInterpolation()
               .setReversed(true)
               .build();


       Path4 = follower
               .pathBuilder()
               .addPath(
                       new BezierLine(new Pose(71.946, 29.889), new Pose(72.000, 30.000))
               )
               .setLinearHeadingInterpolation(Math.toRadians(200), Math.toRadians(50))
               .build();


       Path5 = follower
               .pathBuilder()
               .addPath(
                       new BezierLine(new Pose(72.000, 30.000), new Pose(72.000, 30.000))
               )
               .setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(90))
               .build();
   }
}
