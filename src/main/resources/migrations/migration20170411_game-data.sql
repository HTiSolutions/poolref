ALTER TABLE `pool_ref`.`game`
ADD COLUMN `game_data` JSON NULL AFTER `loser_id`;