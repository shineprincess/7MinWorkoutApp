package com.shineprincess.workoutappp

class ExercisesConstants {

    //static variable == companion object

    companion object {
        //take care of default exercises list

        fun defaultExerciseList() : ArrayList<ExerciseImageInfo> {

            val exerciseList = ArrayList<ExerciseImageInfo>()

            val jumpingJacks = ExerciseImageInfo(
                    1,
                    "Jumping Jacks",
                    R.drawable.ic_jumping_jacks,
                    false,
                    false
            )

            val abdominalCrunch = ExerciseImageInfo(
                    2,
                    "Abdominal Crunch",
                    R.drawable.ic_abdominal_crunch ,
                    false ,
                    false
            )


            val highKneesRunningInPlace = ExerciseImageInfo(
                    3,
                    "High Knees Running In Place" ,
                    R.drawable.ic_high_knees_running_in_place,
                    false,
                    false
            )

            val lunge = ExerciseImageInfo(
                    4,
                    "Lunge",
                    R.drawable.ic_lunge,
                    false,
                    false
            )

            val plank = ExerciseImageInfo(
                    5,
                    "Plank" ,
                    R.drawable.ic_plank,
                    false,
                    false
            )

            val pushUpAndRotation = ExerciseImageInfo(
                    6,
                    "Push Up and Rotation",
                    R.drawable.ic_push_up_and_rotation,
                    false,
                    false
            )

            val sidePlank = ExerciseImageInfo(
                    7,
                    "Side Plank",
                    R.drawable.ic_side_plank,
                    false,
                    false
            )

            val squat = ExerciseImageInfo(
                    8,
                    "Squat Exercise",
                    R.drawable.ic_squat,
                    false,
                    false
            )

            val stepUpOntoChair = ExerciseImageInfo(
                    9,
                    "Step Up Onto The Chair Pretties",
                    R.drawable.ic_step_up_onto_chair,
                    false,
                    false
            )

            val tricepsDipOnChair = ExerciseImageInfo(
                    10,
                    "Triceps Dip On Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,
                    false
            )

            val wallSit = ExerciseImageInfo(
                    11,
                    "Wall Sit Exercise",
                    R.drawable.ic_wall_sit,
                    false,
                    false
            )

            val pushUp = ExerciseImageInfo(
                    12,
                    "Push Up",
                    R.drawable.ic_push_up,
                    false,
                    false
            )

            exerciseList.add(jumpingJacks)
            exerciseList.add(abdominalCrunch)
            exerciseList.add(highKneesRunningInPlace)
            exerciseList.add(lunge)
            exerciseList.add(plank)
            exerciseList.add(pushUpAndRotation)
            exerciseList.add(sidePlank)
            exerciseList.add(squat)
            exerciseList.add(stepUpOntoChair)
            exerciseList.add(tricepsDipOnChair)
            exerciseList.add(wallSit)
            exerciseList.add(pushUp)


            return exerciseList
        }

    }

}